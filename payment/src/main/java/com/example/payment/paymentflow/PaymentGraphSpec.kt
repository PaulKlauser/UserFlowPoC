package com.example.payment.paymentflow

import com.example.payment.paymentflow.destinations.AddressRouteDestination
import com.example.payment.paymentflow.destinations.CardNumberRouteDestination
import com.example.payment.paymentflow.destinations.ConfirmationRouteDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

val paymentGraph = object : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> = mapOf(
        AddressRouteDestination.route to AddressRouteDestination,
        CardNumberRouteDestination.route to CardNumberRouteDestination,
        ConfirmationRouteDestination.route to ConfirmationRouteDestination
    )
    override val route: String = "payment"
    override val startRoute: Route = AddressRouteDestination
}