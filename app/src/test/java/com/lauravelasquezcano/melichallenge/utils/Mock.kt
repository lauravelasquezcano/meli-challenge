package com.lauravelasquezcano.melichallenge.utils

import com.lauravelasquezcano.melichallenge.app.database.DbItem
import com.lauravelasquezcano.melichallenge.domain.Address
import com.lauravelasquezcano.melichallenge.domain.Item
import com.lauravelasquezcano.melichallenge.domain.SearchResponse
import com.lauravelasquezcano.melichallenge.domain.Shipping

val mockedAddress = Address(
    "CO-DC",
    "Bogotá D.C.",
    "TUNPQ0NIQTk2OTI1",
    "Chapinero"
)

val mockedShipping = Shipping(
    true,
    false
)
val mockedItem = Item(
    "MCO608702490",
    "MCO",
    " Moto E6s (2020) 32 Gb Electric Pink 2 Gb Ram",
    369900,
    "COP",
    132,
    221,
    "new",
    "http://http2.mlstatic.com/D_693215-MLA44156431054_112020-I.jpg",
    mockedAddress,
    mockedShipping,
    0
)

val mockedDbItem = DbItem(
    "MCO608702490",
    "MCO",
    " Moto E6s (2020) 32 Gb Electric Pink 2 Gb Ram",
    369900,
    "COP",
    132,
    221,
    "new",
    "http://http2.mlstatic.com/D_693215-MLA44156431054_112020-I.jpg",
    mockedAddress.city_name,
    mockedShipping.free_shipping,
    mockedShipping.store_pick_up
)

val mockedItemList = mutableListOf(
    Item(
        "MCO608702490",
        "MCO",
        " Moto E6s (2020) 32 Gb Electric Pink 2 Gb Ram",
        369900,
        "COP",
        132,
        221,
        "new",
        "http://http2.mlstatic.com/D_693215-MLA44156431054_112020-I.jpg",
        mockedAddress,
        mockedShipping,
        0
    ),
    Item(
        "MCO635431507",
        "MCO",
        "Motorola One Fusion Dual Sim 64 Gb Azul Océano 4 Gb Ram",
        658900,
        "COP",
        10,
        0,
        "new",
        "http://http2.mlstatic.com/D_609736-MLA44155889270_112020-I.jpg",
        mockedAddress,
        mockedShipping,
        0

    )
)
val mockedSearchResponse = SearchResponse(
    "MCO",
    "query",
    mockedItemList
)