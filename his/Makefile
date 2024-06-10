APPNAME=his
VERSION=1.0

all: build-his run

build-his:
	docker build --rm -t $(APPNAME):$(VERSION) . -f Dockerfile
run:
	docker run -d -p 80:80 --name $(APPNAME) $(APPNAME):$(VERSION) --restart=always
