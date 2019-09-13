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

The name of the template is `migae`, passed with the `-t` option.  The
`-n` option is the app name, the `-a` option, a service name.

For help with boot/new:  `$ boot -d boot/new new -h`

TO use locally installed snapshot version of the template, add `-S` argument.

You can repeat the `-a` arg to add as many services as you like.

You must build and save each service separately (`$ boot build target`). Then from my-project `$ boot gae/run` will run all services.

See [boot-gae](https://github.com/migae/boot-gae) for details.

To build a single service (app) just omit the -a args.

## Hacking

Edit the code.  Give it a "-SNAPSHOT" version ID in build.boot, then install it locally:

```
$ boot pom jar install
```

In a test directory, use the snapshot by adding the `-S` argument:

```
$ boot -d boot/new new -t migae -n myapp -S`.
```