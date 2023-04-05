
## Spring Dependency Injection

Dependency injection is a pattern we can use to implement IoC, where the control being inverted is setting an object's dependencies.

Connecting objects with other objects, or “injecting” objects into other objects, is done by an assembler rather than by the objects themselves.

#### Constructor-Based Dependency Injection Example

```
public class Store {
    private final Item item;

    @Autowired
    public Store(Item item) {
        this.item = item;
    }
}

```

#### Field-Based Dependency Injection Example

In case of Field-Based DI, we can inject the dependencies by marking them with an @Autowired annotation:


```
public class Store {
    @Autowired
    private Item item; 
}

```








