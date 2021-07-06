package eu.example.informationbook

class ModelClass() {
    var imageName: String = ""
    var categoryName: String = ""

    constructor(imageName: String, categoryName: String) : this() {
        this.imageName = imageName
        this.categoryName = categoryName
    }

}