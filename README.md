# FlexItemContainer
A "core" library to build novel containers and, ideally, simplify moving non-standard stack sizes between containers.

## Early Plugin
Uses Hyxin to modify Hytales ItemContainer class and InternalContainerUtilItemStack functions to give containers
the ability to control item stack size.

`ItemContainer.getMaxStack(Item item)`

## Plugin
Example of utilizing the foundation set by the Hyxin's to create a new FlexItemContainer class and codec.
```json
{
  // ...
  "State": {
    "Id": "flexContainer",
    "Capacity": 4,
    "StackSize": 4,
    // ...
}
```

# Quick Start
## Early Plugin
1. Download `FlexItemContainer.jar` from [Releases](https://github.com/TannerStevens/FlexItemContainer/releases)
2. Place in server's `earlyplugins/` directory
   - This directory needs to be manually created
   - Server must be launched with `--accept-early-plugins` or `ACCEPT_EARLY_PLUGINS=1`

## Plugin
1. Download `FlexItemContainer.jar` from [Releases](https://github.com/TannerStevens/FlexItemContainer/releases)
2. Place in server's `mods/` directory