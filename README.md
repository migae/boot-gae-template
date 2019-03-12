# boot-gae-template

boot template to generate clojure applications for Google App Engine using the boot-gae build tool.

Usage:

```
$ boot -d boot/new new -t migae -n my-project -a service_a -a service_b
```

Creates:
```
my-project
  build.boot
  service_a
    build.boot
	...etc...
  service_b
    build.boot
	...etc...
```

You can add as many services as you like using the `-a` arg.

You must build and save each service separately (`$ boot build target`). Then from my-project `$ boot gae/run` will run all services.

See [boot-gae](https://github.com/migae/boot-gae) for details.

To build a single service (app) just omit the -a args.
