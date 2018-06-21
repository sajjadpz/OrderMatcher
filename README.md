# OrderMatcher

# Input
There are three types of commands: BUY, SELL and PRINT. The commands should be entered at the
console (stdin) and one command is entered on each line, thus a command ends with a newline.
# BUY
"BUY volume@price" enters a buy order with the specified volume and price. Both price and volume
are positive (> 0) integers. Example "BUY 1000@25" means buy 1000 shares at the price of 25.
# SELL
"SELL volume@price" enters a sell order with the specified volume and price. Both price and volume
are positive (> 0) integers. Example "SELL 500@30" means sell 500 shares at the price of 30.
# PRINT
"PRINT" means that all orders in the order book should be printed to the console (stdout), sorted
with the best price at the top (lowest for sell and highest for buy). The order output is the same as
the order input, example

```sh
--- SELL ---
SELL 100@55
SELL 500@67
SELL 200@88
--- BUY ---
BUY 100@44
BUY 300@33

```
