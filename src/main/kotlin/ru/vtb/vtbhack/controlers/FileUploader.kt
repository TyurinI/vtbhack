package ru.vtb.vtbhack.controlers

import org.springframework.stereotype.Controller
import java.io.FileOutputStream
import java.io.BufferedOutputStream
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import java.io.File


@Controller
class FileUploaderController{
    @RequestMapping(value = "/upload", method = [RequestMethod.POST])
    @ResponseBody
    fun handleFileUpload(@RequestParam("name") name: String,
                         @RequestParam("file") file: MultipartFile){
                val bytes = file.bytes
                val stream = BufferedOutputStream(FileOutputStream(File("$name-uploaded")))
                stream.write(bytes)
                stream.close()
    }
}