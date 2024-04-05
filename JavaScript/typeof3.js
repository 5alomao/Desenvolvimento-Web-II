let nativeStr = "Salomão"
let nativeStrFunction = String("Ferreira")
let strAsAnObject = new String("Junior")

console.log(typeof nativeStr) // string
console.log(typeof nativeStr) // string
console.log(typeof strAsAnObject) // object
console.log('\n')

let isString = strAsAnObject instanceof String
console.log(isString) // true
console.log('\n')

//instanceof só funciona com objetos
console.log(nativeStr instanceof String) //false
console.log(nativeStrFunction instanceof String) //true
console.log('\n')

console.log(nativeStr.constructor == String) //true
console.log(nativeStrFunction.constructor == String) //true
console.log(strAsAnObject.constructor == String) //true

String.prototype.metodoQualquer = function () {
    return this
}

let nome = 'Salomão'
console.log(typeof nome)
console.log(nome.length) // Coersão aqui
let copiaDoNome = nome.metodoQualquer() // Coersão aqui

console.log(typeof copiaDoNome)
console.log(copiaDoNome instanceof String)
console.log(typeof nome)