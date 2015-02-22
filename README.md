# simple-om-draggable

FIXME: Write a one-line description of your library/project.

## Overview

FIXME: Write a paragraph about the library/project and highlight its goals.

## Setup

First-time Clojurescript developers, add the following to your bash .profile:

    LEIN_FAST_TRAMPOLINE=y
    export LEIN_FAST_TRAMPOLINE
    alias cljsbuild="lein trampoline cljsbuild $@"

To avoid compiling ClojureScript for each build, AOT Clojurescript locally in your project with the following:

    lein trampoline run -m clojure.main
    user=> (compile 'cljs.closure)
    user=> (compile 'cljs.core)

Subsequent builds can use:

    lein cljsbuild auto

Clean project specific out:

     lein clean

For more info, read [Waitin'](http://swannodette.github.io/2014/12/22/waitin/).

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
