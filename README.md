# openLCA ILCD API

This is a Java library for reading and writing data sets in the
[ILCD data format](https://eplca.jrc.ec.europa.eu/LCDN/developerILCDDataFormat.xhtml).
It was originally part of the [openLCA modules](https://github.com/GreenDelta/olca-modules)
but was moved to a separate repository since version 2.0.0.

## Usage

Add this dependency to your project:

```xml
<dependency>
  <groupId>org.openlca</groupId>
  <artifactId>olca-ilcd</artifactId>
  <version>3.0.0</version>
</dependency>
```


## New in version 3

### Fluent builder API

The ILCD format defines deeply structured data types. With a fluent API we made it easier to create such structures. Version 2 was based on public properties, you could use them like this (in the most compact form):

```java
var flow = new Flow();
flow.flowInfo = new FlowInformation();
flow.flowInfo.dataSetInfo = new DataSetInfo();
flow.flowInfo.dataSetInfo.uuid = "123abc...";
flow.flowInfo.dataSetInfo.flowName = new FlowName();
flow.flowInfo.dataSetInfo.flowName.baseName.add(
  LangString.of("carbon dioxide", "en"));
```

In version 3, you can write the example above like this:

```java
var flow = new Flow()
  .withFlowInfo()
  .withDataSetInfo()
  .withUUID("123abc...")
  .withFlowName()
  .withBaseName()
  .add(LangString.of("carbon dioxide", "en"));
```

The `with{Field}()` methods will create inner data structures if needed and return them directly. The `with{Field}(value)` methods will set the value of the field and return the instance on which this method was called. For example, `withDataSetInfo()` creates a `DataSetInfo` and binds it to `FlowInfo` if it was not created yet, `withUUID("123...")` sets the value of the `UUID` field and returns the `DataSetInfo` instance, on which then the base name list is created if needed.

### Everything lazy

In version 2, collection fields like lists and maps were all eagerly created and bound to `final` fields, e.g.:

```java
public class Process {
  // ...
  public final List<Exchange> exchanges = new ArrayList<>();

}
```

Now in version 3, they are all `private` and `null` initially:

```java
public class Process {
  // ...
  private List<Exchange> exchanges = null;

  // ...
  public List<Exchange> getExchanges() {
    return exchanges != null
      ? exchanges
      : Collections.emptyList();
  }
}
```

As you can see in the example, the accessor will return an empty list if they are `null`, so it is safe to directly query the returned list. However, you should **never** modify a collection that is returned with a `get*` accessor, use the `with*` builder for this, e.g.:

```java
process.withExchanges().add(
  new Exchange()
    .withFlow(flow)
    .withResultingAmount(42));
```

Collections as any other field can be now simply set to `null` to clear them:

```java
process
  .withProcessInfo(null)
  .withExchanges(null);
```

For querying deeply nested fields we still have the `null` safe utility methods like in version 2. Remember to always check for `null` when using the `get*` methods for non-collection fields:

```java
var pub = Processes.getPublication(process);
if (pub != null) {
  println("Version is: " + pub.getVersion());
}
```

Because of this "laziness" we could remove some wrapper classes from the API and get rid of arrays. Also, the API is  more memory efficient now.


### The `Xml` utility class

Reading and writing ILCD datasets from and to files, streams, strings, byte arrays, etc. is easier with the `Xml` utility class:

```java
var flow = new Flow();
flow.withFlowInfo()
  .withDataSetInfo()
  .withUUID("123abc...")
  .withFlowName()
  .withBaseName()
  .add(LangString.of("carbon dioxide", "en"));

println(Xml.toString(flow));
```

This will print:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<f:flowDataSet
  xmlns:common="http://lca.jrc.it/ILCD/Common" xmlns:f="http://lca.jrc.it/ILCD/Flow">
    <f:flowInformation>
        <f:dataSetInformation>
            <common:UUID>123abc...</common:UUID>
            <f:name>
                <f:baseName xml:lang="en">carbon dioxide</f:baseName>
            </f:name>
        </f:dataSetInformation>
    </f:flowInformation>
</f:flowDataSet>
```
