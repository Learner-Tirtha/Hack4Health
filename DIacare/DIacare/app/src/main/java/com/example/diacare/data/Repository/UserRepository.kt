class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) = userDao.insertUser(user)
    suspend fun authenticateUser(username: String, password: String) = userDao.loginUser(username, password)
}