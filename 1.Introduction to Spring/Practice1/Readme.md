# Alice and Her Bakery — Understanding Spring Dependency Injection

## About the Project

This project demonstrates how the Spring Framework manages object creation and dependencies using a simple bakery example.

Alice wants to bake cakes, and her `CakeBaker` depends on two things: frosting and syrup. Instead of creating concrete frosting and syrup objects directly, the baker depends on their interfaces. Different implementations provide chocolate and strawberry flavors.

Spring manages these objects and injects the required dependencies, allowing the application to focus on its functionality rather than manually connecting every object.

## Core Spring Concepts

### 1. Inversion of Control (IoC)

In a traditional Java application, developers are responsible for creating objects and managing their dependencies.

With Inversion of Control, this responsibility is delegated to the Spring IoC container. Spring creates and manages the configured objects, known as beans, and coordinates their dependencies.

**Key takeaway:** The application delegates object management to the framework instead of controlling the entire object creation process itself.

### 2. Dependency Injection (DI)

Dependency Injection is a technique through which an object receives the dependencies it needs from an external source.

In this project, `CakeBaker` requires `Frosting` and `Syrup`. Rather than creating their implementations internally, it receives them through Spring's dependency injection mechanism.

This reduces tight coupling and makes implementations easier to replace, test, and extend.

### 3. Programming to Interfaces

The baker depends on the `Frosting` and `Syrup` abstractions rather than specific chocolate or strawberry classes.

This follows the principle of programming to an interface, not an implementation.

The benefit is flexibility: the baker can work with different flavor implementations without needing changes to its core logic.

### 4. Spring Beans and the IoC Container

A Spring bean is an object managed by the Spring container.

The container is responsible for creating configured beans, resolving their dependencies, and managing their lifecycle according to the applicable configuration and scope.

In this example, the baker and the required frosting and syrup implementations participate in Spring's object-management system.

### 5. Dependency Resolution

When multiple beans implement the same interface, Spring may not be able to determine which implementation should be injected automatically.

For example, both chocolate and strawberry implementations satisfy the `Frosting` contract. The same situation applies to `Syrup`.

Spring provides mechanisms such as `@Primary` and `@Qualifier` to resolve this ambiguity.

- **`@Primary`** establishes a preferred bean when a single candidate is required and no more specific selection overrides it.
- **`@Qualifier`** narrows the candidate selection using a specified qualifier.

Understanding this distinction is important when designing applications with multiple implementations of the same abstraction.

### 6. Loose Coupling and Maintainability

Because `CakeBaker` depends on interfaces, adding another flavor does not necessarily require modifying the baker itself.

This separation improves maintainability, supports the Open/Closed Principle, and makes it easier to substitute dependencies during testing.

The benefits depend on how the implementations and configuration are designed; using interfaces alone does not automatically guarantee a loosely coupled system.

### 7. Object Creation and Configuration

The project also demonstrates that dependency injection depends on how beans are registered and configured.

Spring can discover components through component scanning or create beans through Java-based configuration. The configuration must be consistent: registering the same implementation through multiple mechanisms can create duplicate candidates and unexpected injection behavior.

## Application Flow

1. Spring discovers or registers the required beans.
2. The container identifies the dependencies required by `CakeBaker`.
3. Spring resolves the appropriate `Frosting` and `Syrup` implementations.
4. The dependencies are injected into the baker.
5. The application retrieves the configured baker bean and invokes its cake-baking operation.

## What I Learned

This exercise helped me understand the relationship between IoC, Dependency Injection, interfaces, and Spring bean management.

It also demonstrated why applications should depend on abstractions instead of concrete implementations, and how Spring handles dependency resolution when multiple beans implement the same interface.

These fundamentals provide a foundation for understanding component scanning, bean lifecycle, configuration classes, application contexts, testing, and more advanced Spring applications.

## Technologies and Concepts

- Java
- Spring Framework
- Maven
- Inversion of Control (IoC)
- Dependency Injection (DI)
- Spring Beans and IoC Container
- Interface-based design
- `@Primary` and `@Qualifier`
- Loose coupling and maintainability

## Further Exploration

Possible extensions include comparing constructor, setter, and field injection; investigating bean initialization and destruction callbacks; exploring `BeanFactory` versus `ApplicationContext`; and writing unit tests with alternative frosting and syrup implementations.

**Learning objective:** Understand not only how to use Spring Dependency Injection, but also why it exists and how it helps structure maintainable Java applications.