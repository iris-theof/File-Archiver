package com.javasampleapproach.filename

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun main() {
    readPatientFilesInDirectory(System.getProperty("user.dir").plus("/Filesystem"),"_FN_LN_BT"
    ,mapOf("FN" to "First Name", "LN" to "Last Name", "BT" to "Birthday"))
}

fun readPatientFilesInDirectory( filesDirectory:String, patternStringInput:String, dictionaryEntries: Map<String, String>) {
    File(filesDirectory).walk().maxDepth(1).forEach {
        if (it.isFile) {
            var fullPath = it.toString()
            println("=================== New Data Found: $fullPath ===================")
            if ( isCompletelyWritten(it) == true ){
                val regex = """(.+)/(.+)\.(.+)""".toRegex()
                val matchResult = regex.matchEntire(fullPath)
                if (matchResult != null) {
                    val (_, fileNameSource, _) = matchResult.destructured
                    extractPatientData( fileNameSource, patternStringInput, dictionaryEntries)
                    archiveNewFile(fileNameSource, fullPath)
                }
            }
        }
    }
}

private fun archiveNewFile(fileNameSource: String, fullPath: String) {
    val currentDateTime = LocalDateTime.now()
    val storePath = (System.getProperty("user.dir").plus("/Filesystem/archive/")).plus(
            currentDateTime.format(DateTimeFormatter.ofPattern("y/M/d")).toString())
            .plus("/").plus(fileNameSource)
    var fileDest = File(storePath)
    var fileDestName = fileDest.name

    if (fileDest.exists()) {
        println("The file $fileDestName has been already saved.")
    } else {
        File(fullPath).copyTo(fileDest)
    }
}

private fun extractPatientData(fileNameSource: String, paternInformation: String, dictionaryEntries: Map<String, String>) {
    println("Patient data will be exported ...")
    val paternArray = paternInformation.split("=|-|_|&|#|:".toRegex()).map { it.trim() }
    val fileArray = fileNameSource.split("=|-|_|&|#|:".toRegex()).map { it.trim() }
    val arrayDictKeys = dictionaryEntries.keys.toList()
    for (index in dictionaryEntries.keys.toList().indices){
        val fnIndex = paternArray.indexOf(arrayDictKeys[index])
        val paternFound = fileArray[fnIndex]
        println(dictionaryEntries.values.toList()[index])
        println(paternFound)}
}

fun String.alphaOnly(): String {
    val regex = Regex("[^A-Za-z]")
    return regex.replace(this, " ")
}

private fun isCompletelyWritten(file: File): Boolean {
    val fileSizeBefore = file.length()
    Thread.sleep(1000)
    val fileSizeAfter = file.length()
    return if (fileSizeBefore == fileSizeAfter) {
        true
    } else {
        println("The data file $file, is being written")
        false
    }
}







