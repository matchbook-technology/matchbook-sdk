![MBSDK_onlight](https://user-images.githubusercontent.com/4140597/66038798-0868d600-e50b-11e9-9055-b51e5f9f5779.png)
# Matchbook SDK

> This library is under development and no stable version has been released yet.
> The API can change at any moment.

[![CircleCI](https://circleci.com/gh/matchbook-technology/matchbook-sdk.svg?style=svg)](https://circleci.com/gh/matchbook-technology/matchbook-sdk)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f91f929fa3184482abb2704f98615f46)](https://www.codacy.com/app/volkodav_s/matchbook-sdk?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=volkodavs/matchbook-sdk&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/f91f929fa3184482abb2704f98615f46)](https://www.codacy.com/manual/volkodav_s/matchbook-sdk?utm_source=github.com&utm_medium=referral&utm_content=volkodavs/matchbook-sdk&utm_campaign=Badge_Coverage)

[![Gitter chat](https://badges.gitter.im/gitterHQ/gitter.png)](https://gitter.im/matchbook-sdk/community)
[![GitHub license](https://img.shields.io/github/license/Naereen/StrapDown.js.svg)](https://github.com/Naereen/StrapDown.js/blob/master/LICENSE)
[![Open Source Love svg2](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)

## Features

*   **High performance**: we take efficiency very seriously
*   **Full support to Matchbook REST API**: all Matchbook powerful REST API is implemented in the SDK
*   **Session management**: user sessions are managed automatically
*   **Configurable**: we provide a number of configuration options to control the SDK behaviour at runtime
*   **Multi-platform**: all Java 8+ versions are supported

## Getting started

![event-animation](https://user-images.githubusercontent.com/4140597/70796614-a5ad7980-1d9a-11ea-8d2f-98dbaa02235f.gif)

### Maven dependency

You should update your _pom.xml_ to pull SNAPSHOT version from Sonatype Release Repository (OSSRH).

```xml
    <repositories>
        <repository>
            <id>oss.sonatype.org-snapshot</id>
            <url>http://oss.sonatype.org/content/repositories/snapshots</url>
            <releases>
                <enabled>false</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>
```

### Examples

#### Configure ConnectionManager

```java
    ClientConfig clientConfig = new ClientConfig.Builder("my-username".toCharArray(), "my-password".toCharArray()).build();
    ConnectionManager connectionManager = new ConnectionManager.Builder(clientConfig).build();
```

#### Get events

```java
    new EventsClientRest(connectionManager).getEvents(new EventsRequest.Builder().build(),
            new StreamObserver<Event>() {
                    @Override
                    public void onNext(Event event) {
                        // add your business logic here
                    }
                    @Override
                    public void onCompleted() {
                        // all messages successfully processed
                    }
                    @Override
                    public <E extends MatchbookSDKException> void onError(E error) {
                        // handle error
                    }
            });
```

#### Submit multiple offers

```java
    OfferPostRequest offerPostRequest = new OfferPostRequest.Builder(
            1295512601550017L,
            Side.BACK,
            new BigDecimal("2.5"),
            new BigDecimal("100")
    ).build();
    List<OfferPostRequest> offers = new ArrayList();
    offers.add(offerPostRequest);
    OffersPostRequest offersPostRequest = new OffersPostRequest.Builder(
            OddsType.DECIMAL,
            ExchangeType.BACK_LAY,
            offers
    ).build();

    new OffersClientRest(connectionManager).submitOffers(offersPostRequest,
            new StreamObserver<Offer>() {
                    @Override
                    public void onNext(Offer offer) {
                        // add your business logic here
                    }
                    @Override
                    public void onCompleted() {
                        // all messages successfully processed
                    }
                    @Override
                    public <E extends MatchbookSDKException> void onError(E error) {
                        // handle error
                    }
            });
````

#### Cancel offer

```java
    new OffersClientRest(connectionManager).cancelOffer(new OfferDeleteRequest.Builder(1000L).build(),
            new StreamObserver<Offer>() {
                    @Override
                    public void onNext(Offer offer) {
                        // add your business logic here
                    }
                    @Override
                    public void onCompleted() {
                        // all messages successfully processed
                    }
                    @Override
                    public <E extends MatchbookSDKException> void onError(E error) {
                        // handle error
                    }
            });
```

## Core modules

*   [matchbook-sdk-core](matchbook-sdk-core)
*   [matchbook-sdk-rest](matchbook-sdk-rest)

## Built with

*   [okhttp](https://square.github.io/okhttp/) - An efficient HTTP client
*   [jackson](https://github.com/FasterXML/jackson) - JSON serialisation/deserialisation
*   [slf4j](https://www.slf4j.org/) - Logging facade for Java
*   [maven](https://maven.apache.org/) - Dependency management system

## How to contribute

Please, read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests to us.

### Code style

Please, use our custom [Code Style](codestyle/matchbookCodeStyle.xml) when contribute to the MB-SDK project.

### Pull requests are welcome

Spotted an error? Something doesn't make sense? Send a [pull request](https://github.com/matchbook-technology/matchbook-sdk/pulls)!
Please avoid making stylistic changes, though. They are unlikely to be accepted. Thanks!

## License

```text
    MIT License

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
```
