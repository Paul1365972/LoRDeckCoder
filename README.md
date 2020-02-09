# ☯ LoRDeckCoder

![Build](https://github.com/Paul1365972/LoRDeckCoder/workflows/Build/badge.svg)

Java 8 library port of [**RiotGames/LoRDeckCodes**](https://github.com/RiotGames/LoRDeckCodes/) to decode/encode [**Legends of Runeterra**](http://playruneterra.com) deck codes.
The project uses no external libraries and is highly configurable.
Created following the guidelines from [**RiotGames/LoRDeckCodes**](https://github.com/RiotGames/LoRDeckCodes/#cards--decks).

## Download

Download the latest release from Gradle:

```gradle
repositories { 
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github.paul1365972:lordeckcoder:v1.0'
}
```

or Maven:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.paul1365972</groupId>
    <artifactId>lordeckcoder</artifactId>
    <version>v1.0</version>
</dependency>
```

## Examples

Simple decoding and encoding:

```java
LoRDeck deck = LoRDeckCoder.toDeck("CEBAIAIFB4WDANQIAEAQGDAUDAQSIJZUAIAQCBIFAEAQCBAA");

String code = LoRDeckCoder.toCode(deck, FormatVersion.LIVE);
```

Parsing and fetching card codes:

```java
LoRCard card = LoRCard.fromCardCode("01PZ019");	
	
String cardCode = card.getCardCode();
LoRFaction faction = card.getFaction();
```

Deck manipulation and analysis:

```java
deck.addCards(LoRCard.fromCardCode("01PZ019"), 2);

int freljordCards = deck.getCards().entrySet().stream()
        .filter(e -> e.getKey().getFaction() == LoRFaction.FRELJORD)
        .mapToInt(Map.Entry::getValue)
        .sum();
```

Custom decoder if you prefer your own implementation:

```java
LoRDeck customDeck = LoRCoder.decode(code, LoRDecoder.from(
        LoRDeck::new,
        (deck, set, factionId, cardNumber, amount) ->
            deck.addCards(new LoRCard(set, factionId, cardNumber), amount)
));
```


## Implementation Details

- All operations on the default implementation objects (LoRDeck, LoRCard) are safe.
This means a deck or card cannot be in an invalid state and is thus always encodable

