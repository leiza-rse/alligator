# Alligator Ontology

The Alligator Ontology consists of Classes and Properties.

**Alligator Event Example:**

```text
@prefix alligator: <http://rgzm.github.io/alligator/ontology/#> .
@prefix ae: <http://example.net/event#> .
@prefix time: <http://www.w3.org/2006/time#> .
@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .
@prefix dc: <http://purl.org/dc/elements/1.1/> .

ae:example
    a alligator:Event ;
    a time:Interval ;
    dc:identifier "example" ;
    rdfs:label "DomitianConsulate2" ;
    alligator:estimatedStart "81.0" ;
    alligator:estimatedEnd "96.0" ;
    alligator:CAx "-0.2646" ;
    alligator:CAy "-0.856" ;
    alligator:CAz "1.0336" ;
    alligator:startFixed "false" ;
    alligator:endFixed "false" ;
    alligator:nextFixedStartNeighbour ae:aDm6bY ;
    alligator:nextFixedEndNeighbour ae:aDm6bY ;
    time:intervalContains ae:example2 .
```


## Classes

### Event

**This is Alligator Event Object.**

= time:Interval

## Properties

### estimatedStart

**Estimated Event Start Date.**

domain: alligator:Event

range: rdfs:Literal (double)

### estimatedEnd

**Estimated Event End Date.**

domain: alligator:Event

range: rdfs:Literal (double)

### CAx

**x-value of the CA.**

domain: alligator:Event

range: rdfs:Literal (double)

### CAy

**y-value of the CA.**

domain: alligator:Event

range: rdfs:Literal (double)

### CAz

**z-value of the CA.**

domain: alligator:Event

range: rdfs:Literal (double)

### startFixed

**Shows if the start date value is fixed.**

domain: alligator:Event

range: rdfs:Literal (boolean)

### endFixed

**Shows if the end date value is fixed.**

domain: alligator:Event

range: rdfs:Literal (boolean)

### nextFixedStartNeighbour

**If the start value is not fixed, the next fixed start neighbour is shown.**

domain: alligator:Event

range: alligator:Event

### nextFixedEndNeighbour

**If the start value is not fixed, the next fixed end neighbour is shown.**

domain: alligator:Event

range: alligator:Event
