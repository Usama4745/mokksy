package dev.mokksy.mokksy

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.string.shouldContain
import io.ktor.client.HttpClient
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class VerifyJournalModeNoneIT {

    private lateinit var mokksy: MokksyServer
    private lateinit var client: HttpClient

    @BeforeEach
    suspend fun setUp() {
        mokksy = MokksyServer(configuration = ServerConfiguration(journalMode = JournalMode.NONE))
        mokksy.startSuspend()
        client = createKtorClient(mokksy.port())
    }

    @AfterEach
    suspend fun tearDown() {
        client.close()
        mokksy.shutdownSuspend()
    }

    @Test
    fun `findAllUnexpectedRequests should throw IllegalStateException when using NONE mode`() {
        val error = shouldThrow<IllegalStateException> {
            mokksy.findAllUnexpectedRequests()
        }
        error.message shouldContain "JournalMode.NONE"
    }

    @Test
    fun `verifyNoUnexpectedRequests should throw IllegalStateException when using NONE mode`() {
        val error = shouldThrow<IllegalStateException> {
            mokksy.verifyNoUnexpectedRequests()
        }
        error.message shouldContain "JournalMode.NONE"
    }
}
