package com.example.swapify_back.cloudinary

import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*
import java.util.concurrent.CompletableFuture

@Service
@Component
class CloudinaryService(private val cloudinary: Cloudinary) {
    /**
     * Sube un archivo a Cloudinary
     * @param file Archivo a subir en base64 o también en multifile
     * @param carpeta Carpeta en la que se guardará el archivo
     * @return Un objeto que te indica el ID de la foto, y también si es video o imagen
     * @throws IOException Si ocurre un error al subir el archivo
     */
    @Throws(IOException::class)
    fun uploadFile(file: Any, carpeta: String?): UploadResult {
        val uploadResult: Map<*, *>

        when (file) {
            // Caso 1: Si es MultipartFile
            is MultipartFile -> {
                if (file.isEmpty) {
                    throw RuntimeException("El archivo está vacío")
                }
                uploadResult = cloudinary.uploader().upload(
                    file.bytes,
                    ObjectUtils.asMap("folder", if (carpeta != null) "Swapify/$carpeta" else "Swapify")
                )
            }

            // Caso 2: Si es Base64
            is String -> {
                // Eliminar el prefijo Base64 (data:image/...)
                val base64Data: String
                try {
                    base64Data = file.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }

                // Decodificar el archivo Base64 a bytes
                val fileBytes = Base64.getDecoder().decode(base64Data)

                uploadResult = cloudinary.uploader().upload(
                    fileBytes,
                    ObjectUtils.asMap(
                        "folder",
                        if (carpeta != null) "Swapify/$carpeta" else "Swapify",
                        "resource_type",
                        "auto"
                    )
                )
            }

            // Si no es base 64 ni multifile
            else -> {
                throw IllegalArgumentException("El archivo debe ser de tipo MultipartFile o Base64 String")
            }
        }

        val upload = UploadResult(uploadResult["public_id"].toString(), uploadResult["resource_type"].toString())


        return upload
    }

    @Async
    fun uploadFileAsync(file: Any, carpeta: String?): CompletableFuture<UploadResult> {
        return CompletableFuture.supplyAsync { uploadFile(file, carpeta) }
    }

    /**
     * Eliminar una imagen de Cloudinary.
     * @param publicId El public_id de la imagen a eliminar.
     * @return Un mapa con el resultado de la operación.
     * @throws Exception Sí ocurre algún error durante la operación.
     */
    @Throws(Exception::class)
    fun deleteImage(publicId: String?): Map<*, *> {
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap())
    }

    @Async
    fun deleteImage(publicId: MutableList<String>){
        for (id in publicId){
            cloudinary.uploader().destroy(id, ObjectUtils.emptyMap())
        }
    }
}