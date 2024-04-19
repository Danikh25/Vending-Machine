# Vending Machine Program
This application simulates a Vending Machine


## Main Features

- Display all items and their respective prices upon program start, with an option to exit.
- Money must be added before making any purchase.
- Display an error if the selected item costs more than the amount in the vending machine.
- Display change returned if the selected item costs equal to or less than the amount added.
- Utilize files to store vending machine items, enabling read/write functionality.
- Update inventory levels appropriately when items are vended. Out-of-stock items are removed from display but retained in memory.

## Architecture and Technologies

- **MVC Pattern**: Follows the Model-View-Controller design pattern for better organization and maintainability.
- **BigDecimal**: Utilizes BigDecimal for accurate financial calculations, ensuring precision.
- **Enums**: Represents the value of coins using enums for clarity and consistency.

