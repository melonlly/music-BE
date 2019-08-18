package com.melon.music.repository

import com.melon.music.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Int> {}