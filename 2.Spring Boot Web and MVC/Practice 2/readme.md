# Custom Validation in Java and Spring Boot

## Overview

This module explores Java custom annotations and declarative validation using Spring Boot and Jakarta Bean Validation.

The primary objective is to understand how custom annotations are declared, how validation rules are implemented, and how a validation provider discovers and executes those rules at runtime.

Instead of scattering validation logic throughout controllers and services, custom constraints allow rules to be expressed declaratively and reused across application components.

## Learning Objectives

- Understand `interface` versus `@interface`.
- Create custom Java annotations.
- Understand annotation metadata, retention policies, and targets.
- Implement custom constraints using `ConstraintValidator`.
- Understand the roles of `@Constraint`, `@Valid`, and `@NotNull`.
- Trace request validation through Spring MVC.
- Connect Java annotations with JVM class loading and reflection.
- Understand how validation errors become HTTP responses.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language and runtime platform |
| Spring Boot | Application configuration and component infrastructure |
| Spring MVC | HTTP request handling |
| Jakarta Bean Validation | Declarative validation API |
| Hibernate Validator | Common Bean Validation implementation |
| Maven | Dependency management and build automation |
| IntelliJ IDEA | Development and debugging |

> **Dependency note:** The project must include a compatible validation implementation. In Spring Boot, `spring-boot-starter-validation` is commonly used to provide Jakarta Bean Validation and Hibernate Validator.

## 1. Java `interface` vs `@interface`

### Regular interface

A Java interface defines a contract that implementing classes must satisfy.

```java
public interface PaymentService {
    void pay();
}
```

### Custom annotation

The `@interface` keyword declares a new annotation type.

```java
public @interface PrimeNumber {
}
```

An annotation is a special kind of Java type used to attach metadata to program elements such as classes, fields, methods, and parameters.

The distinction is:

- `interface` defines a contract for implementing classes.
- `@interface` declares an annotation type.
- `@PrimeNumber` applies that annotation to a program element.

## 2. Why Custom Validation Annotations?

Suppose an application requires a field to contain a prime number.

Java provides common validation constraints such as `@NotNull`, `@NotBlank`, `@Min`, and `@Max`. However, a prime-number constraint is application-specific.

Without a custom constraint, developers may repeat conditional checks in multiple controllers or services.

A custom annotation expresses the rule at the point where it is needed:

```java
@PrimeNumber
private Integer number;
```

This separates three responsibilities:

1. **Constraint declaration:** `@PrimeNumber` defines the annotation and its metadata.
2. **Constraint implementation:** `PrimeNumberValidator` determines whether a value is valid.
3. **Validation execution:** Spring MVC and the Bean Validation provider coordinate validation when triggered.

An annotation alone does not execute validation logic.

## 3. Anatomy of a Custom Constraint

A typical custom constraint contains the following metadata:

```java
@Documented
@Constraint(validatedBy = PrimeNumberValidator.class)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimeNumber {

    String message() default "Number must be prime";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

### `@Constraint`


@Constraint(validatedBy = PrimeNumberValidator.class)


Connects the annotation to the validator implementation recognized by the Bean Validation provider.

### `@Target`

Specifies where the annotation can be applied.

- `ElementType.FIELD`: fields.
- `ElementType.PARAMETER`: method or constructor parameters.
- `ElementType.ANNOTATION_TYPE`: other annotation declarations.

### `@Retention`


@Retention(RetentionPolicy.RUNTIME)


Makes annotation metadata available at runtime through Java reflection.

This is important for runtime frameworks that inspect class and field metadata to discover constraints.

### `message()`

Defines the default constraint violation message.


String message() default "Number must be prime";


The message can be overridden at the usage site.

### `groups()`

Allows constraints to be organized into groups and validated selectively.

### `payload()`

Provides an extension mechanism for metadata associated with a constraint.

These elements form part of the standard Bean Validation constraint definition.

## 4. Implementing `ConstraintValidator`

The validator contains the actual business rule.

```java
public class PrimeNumberValidator
        implements ConstraintValidator<PrimeNumber, Integer> {

    @Override
    public boolean isValid(
            Integer value,
            ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        if (value < 2) {
            return false;
        }

        for (int i = 2; i <= value / i; i++) {
            if (value % i == 0) {
                return false;
            }
        }

        return true;
    }
}
```

### Understanding the generic parameters


ConstraintValidator<PrimeNumber, Integer>


- `PrimeNumber` identifies the constraint annotation.
- `Integer` identifies the value type supported by the validator.

The Bean Validation provider invokes `isValid()` to evaluate the constraint.

### Why return `true` for `null`?

Nullability and primality are separate rules.

A null value is not a prime number, but a prime-number validator normally leaves requiredness to a separate constraint:

```java
@NotNull
@PrimeNumber
private Integer number;
```

This makes the custom constraint more reusable.

### Algorithm complexity

The validator tests potential divisors up to the square root of the number.

- **Time complexity:** \(O(\sqrt{n})\) for positive integer input.
- **Auxiliary space complexity:** \(O(1)\).

The loop condition `i <= value / i` avoids the potential integer overflow associated with calculating `i * i`.

## 5. Applying the Constraint to a DTO

```java
public class PrimeNumberRequest {

    @NotNull
    @PrimeNumber
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
```

The DTO represents the incoming request data.

The annotation describes the validation rule, while the DTO itself carries the value to be checked.

## 6. Triggering Validation in Spring MVC

```java
@RestController
@RequestMapping("/validation-demo")
public class ValidationController {

    @PostMapping("/prime")
    public String checkPrime(
            @Valid @RequestBody PrimeNumberRequest request) {

        return request.getNumber() + " is prime";
    }
}
```

The important annotations are:

- `@RestController`: declares a controller whose return values are written to the HTTP response body.
- `@RequestMapping`: establishes a base request path.
- `@PostMapping`: maps an HTTP POST request.
- `@RequestBody`: binds the HTTP request body to a Java object.
- `@Valid`: requests validation of the bound object and its applicable constraints.

The validation starter must be present for the standard Spring Boot validation integration to work.

## 7. Example HTTP Requests

### Valid request

```http
POST /validation-demo/prime
Content-Type: application/json
```

```json
{
  "number": 7
}
```

The number passes validation, so the controller method can proceed.

### Invalid request

```json
{
  "number": 9
}
```

The number fails the prime-number constraint because it is divisible by 3.

Spring MVC normally returns HTTP `400 Bad Request` for request-body validation failures. The precise error response depends on the application's exception-handling configuration.

## 8. JVM Architecture and Annotation Metadata

Custom annotations connect naturally to JVM class loading and reflection.

### Compilation

The Java compiler converts source files into `.class` files containing JVM bytecode and relevant metadata.

### Runtime retention

An annotation declared with `RetentionPolicy.RUNTIME` remains available to runtime reflection.

For example:

```java
Field field =
        PrimeNumberRequest.class.getDeclaredField("number");

PrimeNumber annotation =
        field.getAnnotation(PrimeNumber.class);
```

This demonstrates that annotation metadata can be discovered programmatically at runtime.

### Validation provider

The Bean Validation provider, commonly Hibernate Validator, interprets constraint metadata and invokes the associated validator.

### Request lifecycle

A typical request follows this sequence:

1. The embedded servlet container receives the HTTP request.
2. Spring MVC binds JSON to the request DTO.
3. `@Valid` triggers Bean Validation.
4. The validation provider evaluates the registered constraints.
5. The validator reports any constraint violations.
6. Spring MVC handles the validation result.
7. A valid request continues to the controller method; an invalid request normally produces a client error response.

**Important distinction:** `@interface` does not implement validation itself. It declares metadata that the validation infrastructure can interpret.

## 9. Validation vs Manual Conditional Checks

| Manual validation | Declarative validation |
|---|---|
| Conditions are called explicitly | Constraints are declared on program elements |
| Logic can become duplicated | Rules can be reused |
| Error handling must be coordinated manually | Framework integration can handle violations consistently |
| Validation can be forgotten at a call site | Validation can be triggered at defined application boundaries |

Custom constraints are especially useful when the same domain rule must be enforced consistently across multiple DTOs or endpoints.

## 10. Common Mistakes

- Declaring `@interface` but not connecting the annotation to a validator.
- Omitting `@Retention(RetentionPolicy.RUNTIME)` when runtime discovery is required.
- Using an annotation on a location not allowed by `@Target`.
- Forgetting `@Valid` or another validation trigger.
- Omitting the validation dependency.
- Assuming a custom validator automatically rejects null values.
- Assuming `@Valid` itself contains the validation algorithm.
- Expecting an annotation to execute arbitrary code merely because it appears on a field.



## Key Takeaway

A custom validation annotation separates the **declaration of a rule** from the **implementation of the rule** and the **framework mechanism that executes it**.

`@interface` declares the annotation type, `@Constraint` connects it to a validator, `ConstraintValidator` implements the rule, and Spring MVC integrates validation into the HTTP request lifecycle.
