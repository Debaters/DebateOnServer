package com.debaters.debateOnServer.service

import org.springframework.stereotype.Service

@Service
class NicknameService {
    private var nicknames = mutableMapOf<String, String>()

    @ExperimentalStdlibApi
    suspend fun getNickNameById(userAgent: String): String {
        if (nicknames.containsKey(userAgent)) {
            return nicknames.getValue(userAgent)
        }

        return createNewNickname(userAgent)
    }

    private fun createNewNickname(id: String): String{

        val nameSize = 2
        while(true) {
            var newNickname = ""
            for(i in 0..nameSize) {
                val ch: Char = ((Math.random() * 11172) + 0xAC00).toChar()
                newNickname += ch
            }
            print("newNickname : $newNickname")
            if (!nicknames.containsKey(newNickname)) {
                nicknames[id] = newNickname
                return newNickname;
            }
        }
    }

}