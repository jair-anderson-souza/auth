package tests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class TransactionSimulation extends Simulation {

  object Create {

    val file = csv("create-transactions.csv").random
    val scenarioCreate = scenario("Create_New_Transaction")
      .feed(file)
      .exec(
        http("transaction").post("/transaction").body(StringBody((""" { "account": "${account}", "totalAmount" : "${totalAmount}", "mcc" : "${mcc}" ,"merchant" : "${merchant}" } """)))
      ).pause(2.seconds)

  }


  val httpProtocol = http.baseUrl("http://localhost:8080").header("Content-Type", "application/json")

  val requests = scenario("transactions").exec(Create.scenarioCreate)


  setUp(
    requests.inject(
      rampUsersPerSec(0).to(1000).during(1.minutes)
    ),
    //    users.inject(
    //      rampUsersPerSec(0).to(100).during(10.minutes)
    //    )
  ).protocols(httpProtocol)
}
