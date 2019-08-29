# HATEOAS / HAL example

What you need to let the client decide which format he wants:
 * spring.hateoas.use-hal-as-default-json-media-type=false in application.properties
 * produces = {"application/json", "application/hal+json"} to your GetMapping

Return JSON format:

`bash
$ curl http://localhost:8080/example/greetingInfoMessage?name=Hugo
`

Return HAL format:

`bash
curl --header "Accept: application/hal+json" http://localhost:8080/example/greetingInfoMessage?name=Hugo
`

Return JSON format:

`bash
curl --header "Accept: application/json" http://localhost:8080/example/greetingInfoMessage?name=Hugo
`
