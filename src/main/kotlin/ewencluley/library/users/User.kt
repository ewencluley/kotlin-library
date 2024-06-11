package ewencluley.library.users

import java.util.UUID

/**
 * A library user who can interact with the Library and perform actions.
 */
data class User(val id: UUID = UUID.randomUUID())
