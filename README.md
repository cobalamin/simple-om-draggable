# simple-om-draggable

A simple draggable wrapper component for Om

## Overview

I built this thing to fix a few issues I experienced with [Anna Pawlicka's](http://annapawlicka.com/draggable-wrapper-component-with-om-and-core-async/) and [neo / felixflores'](https://github.com/neo/ff-om-draggable) draggable components. It's obviously also based on both :)

This component works with multiple draggables overlapping each other, because it listens to mousemove and mouseup events globally. It also protects the user from accidentally clicking a link that they hovered over when they started dragging (via an invisible overlay - it's not beautiful, but it works).

For a quick demo, have a look at https://github.com/cobalamin/omnom, the project I built this component for.

## License

Copyright © 2015 Simon Welker

Distributed under the MIT License.
