# simple-om-draggable

[![Clojars Project](http://clojars.org/simple-om-draggable/latest-version.svg)](http://clojars.org/simple-om-draggable)

A simple draggable wrapper component for Om

## Overview

I built this thing to fix a few issues I experienced with [Anna Pawlicka's](http://annapawlicka.com/draggable-wrapper-component-with-om-and-core-async/) and [neo / felixflores'](https://github.com/neo/ff-om-draggable) draggable components. It's obviously also based on both :)

Usage:
 - Add the clojars repository to your project.clj dependencies.
 - Require the `draggable-item` wrapper fn in your namespace:

   `(:require [simple-om-draggable.core :refer [draggable-item]])`

 - It has two arities, `[view pos-keys] [view pos-keys pos-key-map]`.
   - `view` is your component
   - `pos-keys` is a vector that describes the path to the position state in the component's cursor
   - `pos-key-map` is a map that describes what keys in the position state cursor to use as **top** and **left** offset (optional, defaults to `{:left :x, :top :y}` - so the left offset is saved in `:x`, the top offset is saved in `:y`)

This component works with multiple draggables overlapping each other, because it listens to mousemove and mouseup events globally. It also protects the user from accidentally clicking a link that they hovered over when they started dragging (via an invisible overlay - it's not beautiful, but it works).

## Example

Run `lein cljsbuild once`, then open up `resources/public/index.html`.

## License

Copyright © 2015 Simon Welker

Distributed under the MIT License.
