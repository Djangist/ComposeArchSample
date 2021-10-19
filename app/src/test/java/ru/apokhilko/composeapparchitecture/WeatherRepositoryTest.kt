package ru.apokhilko.composeapparchitecture

import junit.framework.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import ru.apokhilko.composeapparchitecture.data.WeatherDataRepositoryImpl

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
object WeatherRepositoryTest: Spek( {

    val repository = WeatherDataRepositoryImpl()

    describe("init") {
        it("test"){
            assertEquals(1+1, 2)
        }
    }
})