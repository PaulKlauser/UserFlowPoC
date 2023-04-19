# User Flow PoC

Shows one way to build a user flow with a nav-scoped ViewModel. This allows
state to be shared throughout the flow, but ensures that it doesn't
persist beyond the flow at all.

A common alternative to this is to use some global repository within the flow, 
however you risk forgetting to properly clear it out after the flow.

![Flow Diagram](flow.png?raw=true)