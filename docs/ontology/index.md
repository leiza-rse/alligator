# Alligator Ontology

The Alligator Ontology consists of Classes and Properties.

**Alligator Event Example:**

```text
@prefix alligator: <http://rgzm.github.io/alligator/ontology#> .
@prefix ae: <http://example.net/event#> .
@prefix time: <http://www.w3.org/2006/time#> .
@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .
@prefix dc: <http://purl.org/dc/elements/1.1/> .

ae:example
    a alligator:event ;
    a time:Interval ;
    dc:identifier "example" ;
    rdfs:label "DomitianConsulate2" ;
    alligator:estimatedstart "81.0" ;
    alligator:estimatedend "96.0" ;
    alligator:cax "-0.2646" ;
    alligator:cay "-0.856" ;
    alligator:caz "1.0336" ;
    alligator:startfixed "false" ;
    alligator:endfixed "false" ;
    alligator:nfsn "Example 3" ;
    alligator:nfen "Example 3" ;
    time:intervalContains ae:example2 .
```


## Classes

### event

**This is Alligator Event Object.**

= time:Interval

## Properties

### estimatedstart

**Estimated Event Start Date.**

domain: alligator:event

range: rdfs:Literal (double)

### estimatedend

**Estimated Event End Date.**

domain: alligator:event

range: rdfs:Literal (double)

### cax

**x-value of the CA.**

domain: alligator:event

range: rdfs:Literal (double)

### cay

**y-value of the CA.**

domain: alligator:event

range: rdfs:Literal (double)

### caz

**z-value of the CA.**

domain: alligator:event

range: rdfs:Literal (double)

### startfixed

**Shows if the start date value is fixed.**

domain: alligator:event

range: rdfs:Literal (boolean)

### endfixed

**Shows if the end date value is fixed.**

domain: alligator:event

range: rdfs:Literal (boolean)

### nfsn

**If the start value is not fixed, the next fixed start neighbour is shown.**

domain: alligator:event

range: alligator:event

### nfen

**If the start value is not fixed, the next fixed end neighbour is shown.**

domain: alligator:event

range: alligator:event
