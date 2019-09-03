# HATEOAS / HAL example

What you need to let the client decide which format he wants:
 * spring.hateoas.use-hal-as-default-json-media-type=false in application.properties
 * produces = {"application/json", "application/hal+json"} to your GetMapping

Return JSON format:

`bash
$ curl http://localhost:8080/context/greeting/hugo
`

Return HAL format:

`bash
curl --header "Accept: application/hal+json" http://localhost:8080/context/greeting/infoMessage/hugo
`

Return JSON format:

`bash
curl --header "Accept: application/json" http://localhost:8080/context/greeting/infoMessage/hugo
`

## Erkenntnisse
* Wenn der JSON-Style zurückkommt, sieht das so aus (Array):
```
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8080/context/greeting/daniel",
        "hreflang": null,
        "media": null,
        "title": null,
        "type": null,
        "deprecation": null
      }
```
* Wenn der HAL-Style zurückkommt, sieht das so aus (Map - der Key ist "rel", der Value ist dann das Objekt):
```
    "_links": {
      "self": {
        "href": "http://localhost:8080/context/greeting/daniel"
      }
    }
```
* Spring Fox generiert ein .json, welches folgendermassen aussieht:
```
Greeting: {"type":"object","properties":{"_links":{"type":"array","items":{"$ref":"#/definitions/Link"}},"content":{"type":"string"}},"title":"Greeting"}
```
* Wir haben hier also ein "Zwischending": auf der einen Seite wird ein Array angegeben, auf der anderen Seite heisst es aber `_links`.
* Dies obwohl die Klasse in spring-hateoas (`ResourceSupport`) eigentlich `@JsonProperty("links")` über der Liste hat.
* Warum ändert sich das nun in Spring Fox? In spring-hateoas gibt es die Klasse `org.springframework.hateoas.hal.ResourceSupportMixin`, welche ResourceSupport erweitert. Dort findet man `_links`. Ausserdem sind dort Serializer und Deserializer definiert. Es gibt bspw. den `HalLinkListSerializer`, welcher aus einer Liste von Links eine Map baut.
* Offenbar erkennt Spring Fox zwar die Annotation im Mixin (`_links` statt `links`), nicht aber was der Serializer nachher eigentlich macht. Das erklärt, warum zwar ein Array generiert wird, dies aber `_links` heisst.

## Lösung
* Man kann die Definition im Swagger JSON umbauen:
```
        "_links":{
          "type":"array",
          "items":{
            "$ref":"#/definitions/Link"
          }
        }
```

wird dann zu

```
        "_links":{
          "type":"object",
          "additionalProperties":{
            "$ref": "#/definitions/Link"
          }
        }
```
was eine Map darstellt.

Dies erreicht man über die 

