package tests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class TransactionSimulation extends Simulation {

  object Create {

    val file = csv("create-transactions.csv").random
    val scenarioCreate = scenario("Create_New_Transaction")
      .feed(file)
      .exec(
        http("transaction").post("/transaction")
          .body(StringBody((""" { "account": "${account}", "totalAmount" : "${totalAmount}", "mcc" : "${mcc}" ,"merchant" : "${merchant}" } """)))
          .check(status.is(200)
          )
    )
  }

  val httpProtocol = http.baseUrl("http://localhost:8181").header("Content-Type", "application/json")

  val requests = scenario("transactions").exec(Create.scenarioCreate)

  setUp(
    requests.inject(
      rampConcurrentUsers(1).to(2).during(30.seconds) // 2
    ),
  ).protocols(httpProtocol)
}
