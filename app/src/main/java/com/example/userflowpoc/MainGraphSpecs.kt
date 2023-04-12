package com.example.userflowpoc

import com.example.payment.paymentflow.destinations.PaymentFlowRouteDestination
import com.example.payment.paymentflow.paymentGraph
import com.example.userflowpoc.destinations.MainRouteDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

val mainGraph = object : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> = mapOf(
        MainRouteDestination.route to MainRouteDestination,
        PaymentFlowRouteDestination.route to PaymentFlowRouteDestination
    )
    override val route: String = "main"
    override val startRoute: Route = MainRouteDestination
    override val nestedNavGraphs: List<NavGraphSpec> = listOf(paymentGraph)
}