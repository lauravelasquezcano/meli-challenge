# meli-challenge

Proyecto creado para la solución del desafío propuesto por Mercado Libre.

Se utilizó **navigation** para la navegación entre fragmentos. Se implementaron las siguientes 3 vistas: **búsqueda, resultados y detalle**.

Los resultados de la búsqueda no se almacenan en la base de datos. Solamente el item seleccionado será almacenado para ver el detalle.
La información relacionada a este detalle se extraerá de la base de datos la cuál está construida con **Room**.

Tecnologías utilizadas: 
- Clean architecture (metodología)
- MVVM (Patrón de diseño)
- Android Studio (IDE): Chipmunk 2021.2.1 Patch 1
- Kotlin: 1.6.10
- Hilt (inyector de dependencias): 2.40
- Room (base de datos): 2.4.2
- Mockk (pruebas unitarias): 1.10.5
- JUnit (pruebas de UI): 4.13.2
- Espresso (pruebas de UI): 3.4.0

**NOTA:**
Se inició la implementación de pruebas de UI, para lo cual fue necesario crear una EmptyActivity en la cual cargar los fragmentos a probar. Se creó el CustomTestRunner utilizando el HilTestApplication. Se implementó la función launchFragmentInHiltContainer para cargar los fragmentos a probar en pantalla. 

Desafortunadamente se obtuvo un error al ejecutar la prueba más simple del fragmento search y no fue posible continuar con la implementación de las demás pruebas.
