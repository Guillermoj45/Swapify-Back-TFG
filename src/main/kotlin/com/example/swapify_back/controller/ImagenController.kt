package com.example.swapify_back.controller

import com.example.swapify_back.cloudinary.CloudinaryService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Controller
class ImagenController(
    private val cloudinaryService: CloudinaryService
) {

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile,
                   @RequestParam("file2") file2:MultipartFile): ResponseEntity<String> {
        try {
            val url = cloudinaryService.uploadFileAsync(file, carpeta = null)
            val url2 = cloudinaryService.uploadFileAsync(file2, carpeta = null)

            val imagen = url.join()
            val imagen2 = url2.join()

            return ResponseEntity.ok("Archivo subido con éxito: ${imagen.publicId} y ${imagen2.publicId}")
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body("Error al subir el archivo: " + e.message)
        }
    }

    @PostMapping("/upload2")
    fun uploadFile2(@RequestParam("file") file: MultipartFile, @RequestParam("file2") file2:MultipartFile): ResponseEntity<String> {
        try {
            val url = cloudinaryService.uploadFile(file, carpeta = null)
            val url2 = cloudinaryService.uploadFile(file2, carpeta = null)


            return ResponseEntity.ok("Archivo subido con éxito: ${url.publicId} y ${url2.publicId}")
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body("Error al subir el archivo: " + e.message)
        }
    }
}