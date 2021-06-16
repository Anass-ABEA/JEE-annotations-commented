# JEE

## Enumeration

You can create a simple enumeraction like this : 
```
public enum Enumeration{
    VAUE1, VALUE2;
}
```
You can create Enumeration with an attribute with a private constructor and a getter
```

public enum EnumerationWithAttribute{
    VALUE1(100), VALUE2(210);

    public int val;
    private EnumerationWithAttribute(int val){
        this.val = val;
    }
    public int getVal(){return this.val);
}
```
You can create Enumeration with many attributes with a private constructor and some getters
```
public enum EnumerationWithTwoAttributes{
    VALUE1(100,"this is value 1"), VALUE2(210, "this is value2");

    public int val;
    public String description;

    private EnumerationWithAttributes(int val, String description){
        this.val = val;
        this.description = description;
    }
    public int getVal(){return this.val);
    public String getDescription(){return this.description);
}
```

Enum can be called like a public static attribute of a static class
`private EnumerationWithTwoAttributes valeur1 = EnumerationWithTwoAttributes.VALUE1`

## Enheritance
**hypothesis :**
```
class A { private int param1; private int id; }
class B extends A{ private int param2; }
```
_______
### InheritanceType.JOINED
Use the annotation :
`@Inheritance(strategy = InheritanceType.JOINED)`  before the definition of the partent class `A`\

creates 2 like so : 

Table A : 

| id | param1 |
| :---: | :---: |
| 1 | 290 | 
| 1 | 290 |
| 1 | 290 | 

Table B : 

| id | param2 |
 | :---: | :---: |
 | 1 | 50 |


### InheritanceType.SINGLE_TABLE
Use the annotation :
 `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)` before the definition of the partent class `A`\

creates one table like so : 

| id | param1 | param2 |
| :---: | :---: | :---: |
| 1 | 290 | 50 |
| 1 | 290 | NULL |
| 1 | 290 | NULL |



### InheritanceType.TABLE_PER_CLASS
**HINT :**  _most if the time it is used with absract classes so that the parent class cannot be instanciated and thus the table associated will not be generated_\

Use the annotation :
 `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)` before the definition of the partent class `A`

creates 2 like so : 

Table A : 

| id | param1 |
| :---: | :---: |
| 1 | 290 | 
| 1 | 290 |
| 1 | 290 | 

Table B : 

| id | param1 | param2 |
| :---: | :---: | :---: |
| 1 | 290 | 50 |

## Relationships

### One to One
**Example classes :**
```
class A { 
    private int id1; 
    private int param1; 
    private B b;
}

class B { 
    private int id2; 
    private int param2; 
    private A a;
}
```
_______

The order here matters!! 

the class where you add this annotations will represent the class of the object you should create first 

Let's say we want to start with the class `A`, inside this class and before the attribute `private B b;` we add the following annotaitons

```
@OneToOne()
@JoinColumn(name = "id1", referencedColumnName = "id2")
```

**NOTE :** the name references the ID of the current class, in this example `A` and the referencedColumn 
references the id to be associated with, in this example the attribute `id2` inside the class `B`

we should also add the annotations in the class `B`
```
@OneToOne()
@JoinColumn(mappedBy = "b")
```

The element inside mappedBy references the Object name that has the type of the current class in the other class (the class to be created first).
So if we change the reference in class `A` from `private B b;` to `private B attributeOfClassB;` we will change the `mappedBy` 
from `b` to `attributeOfClassB`;

Result : 

```
class A { 
    @Id
    private int id1; 
    private int param1; 

    @OneToOne()
    @JoinColumn(name = "id1", referencedColumnName = "id2")
    private B attributeOfClassB;
}

class B { 
    @Id
    private int id2; 
    private int param2; 

    @OneToOne()
    @JoinColumn(mappedBy = "attributeOfClassB")
    private A a;
}
```

**IMPORTANT NOTE:** in this situation to create an object and insert it in the database we should create
an instance of `A` with its `matricule` and then create an instance of `B` where we will pass the instance of `A` as a a
parameter like so : 

```
A a = new A(1,3);
B b = new B(3 , a);
```

The constructor of B should take the `id2` by doing `this.id2 = a.getId();` and saving the attribute a 
`this.a = a;`


### One to Many
**Example classes :**
```
class A { 
    private int id1; 
    private int param1; 
    private List<B> listOfBs;
}

class B { 
    private int id2; 
    private int param2;
    private A a;
}
```
_______

in this example the One-To-Many is quite simple.

In the class that has the List you add the annotations `A`
```
@OneToMany()
@MappedBy("a")
```

In the other class we add the annotations `B`

```
@ManyToOne()
@JoinColumn(name="a_id")
```

Result is : 

```
class A { 
    @Id
    private int id1; 
    private int param1; 

    @OneToMany()
    @MappedBy("a")
    private List<B> listOfBs;
}

class B { 
    @Id
    private int id2; 
    private int param2;

    @ManyToOne()
    @JoinColumn(name="a_id")
    private A a;
}
```

The SQL tables will be : 

Table A : 

| id | id1 | param1 |
| :---: | :---: | :---: |
| 1 | 290 | 50 |

Table B : 

| id | id2 | param2 | a_id |
| :---: | :---: | :---: | :---: |
| 1 | 290 | 50 | 1 |

**IMPORTANT NOTE:** To insert an element in the table we follow the same logic as in One-To-One

```
A a = new A(1, 3);
B b = new B(3, 1, a);
```

The constructor of B should take the attributes and the Object it is associated with.
 We save the attribute a like so: 
`this.a = a;`

We don't need to use the same ID since it's One to Many the same id goes only for One to One

Note that `B` is the class that has picked the column name to be added (`a_id`)

### Many to Many
**Example classes :**
```
class A { 
    private int id1; 
    private int param1; 
    private List<B> listOfBs;
}

class B { 
    private int id2; 
    private int param2;
    private List<A> listOfAs;
}
```
_______

In one class, we add the annotation, example `A` class
```
@ManyToMany(fetch = FetchType.EAGER, mappedBy = "listOfAs")
```

**PS : ** The fetch type has 2 values `FetchType.EAGER` to get all the data at in one sql query or `FetchType.LAZY` where
only one part of the data is loaded and the other part can be delayed.

In the other class, we add the annotation, example `B` class.
```
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name = "As_Bs", 
            joinColumns = @JoinColumn(name = "id_B"), 
            inverseJoinColumns = @JoinColumn(name = "id_A"))
	
```

Result is : 
```
class A { 
    @Id
    private int id1; 
    private int param1; 

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "listOfAs")
    private List<B> listOfBs;
}

class B { 
    @Id
    private int id2; 
    private int param2;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "As_Bs", 
                joinColumns = @JoinColumn(name = "id_B"), 
                inverseJoinColumns = @JoinColumn(name = "id_A"))
    private List<A> listOfAs;
}
```

in this case a new table will be created with the name picked, `As_Bs` in this example, and it will have 2 columns : 
`id_B` that will reference the ids of the rows from the B table and a column `id_A` that will reference the ids of the rows from the A table

**IMPORTANT NOTE:** To insert an element in the table we follow the same logic as in One-To-One

```
A a = new A(1,3);
B b = new B(3, 4);
b.addToList(a);
```

here we will add the object `a` to the list inside the instance of `B` class. 

Note that B is the one that created the table (specified the name and the columns)

## Composed Primary Key? 

in JEE you can NOT add the annotaiton `@Id` twice. To add a composed primary key ( 2 or more ), you should create a new class with the annotation 
 `@Embeddable` like such : 

Do not forget to add the serialValueUID ( it is most of the time generated automatically when you implement the interface `Serializable`)

You also need to Override the methods `equals(Object o)` et `hasCode()` 
```
@Embeddable
public class ComposedPrimaryKey implements Serializable{
    private static final long serialVersionUID = 5425227757512114629L;
    private String idPart1;
    private String idPart2;
    
    @Override
    public int hashCode(){
        // return a unique hashcode here.
        // preferably use classes to use the method hashCode inside 
        // avoid using int or float or long , use Long, Integer or Float 
    }
}
```

and in the class you want to use the composed primary key you can add it with the annotaiton 
 `@EmbeddedId` like so 

```
public class MyClass{

    @EmbeddedId
    private ComposedPrimaryKey myPrimaryKey;
}
```


Each time you want to use it in relationships you need to add a dot to specify which one to use

using the previous example : 

```
public class A{
    @Id
    private int ID;
       
    @OneToMany(mappedBy="myPrimaryKey.idPart1")
    private List<MyClass> listeMyClass = new ArrayList<>();
}

public class MyClass{

    @EmbeddedId
    private ComposedPrimaryKey myPrimaryKey;
}

@Embeddable
public class ComposedPrimaryKey implements Serializable{
    private static final long serialVersionUID = 5425227757512114629L;

    @ManyToOne
    @JoinColumn(name = "myclass_id")
    private String idPart1;

    private String idPart2;
    
    @Override
    public int hashCode(){
        // return a unique hashcode here.
        // preferably use classes to use the method hashCode inside 
        // avoid using int or float or long , use Long, Integer or Float 
    }
}
```

## Repositories


