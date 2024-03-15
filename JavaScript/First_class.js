function somaA(op1, op2) {
    return op1 + op2;
}

const soma = function (op1, op2) {
    return op1 + op2;
};

const sub = function (op1, op2) {
    return op1 - op2;
};

const a = somaA(1, 2);
console.log(a);
console.log(typeof a);

console.log(somaA(5, 5));

const b = soma("5", 5);
console.log(b);
console.log(typeof b);


// soma = 2 + 2;
// console.log(soma);
// console.log(typeof soma);
// não se pode mudar os valores, pois a variável 'soma' está como (const), para mudar tem q trocar para (let)


function hof(operation, writer, op1, op2) {
    writer(operation(op1, op2));
}

hof(sub, console.log, 1, 2);

//higher order function
function maiorQue(writer) {
    let num = (Math.random() * 100).toFixed();
    console.log("Random: " + num);

    return function (valor) {
        return valor > num
    };
}

// console.log(maiorQue()(50));

//Usando uma variavel intermediaria
const funcRef = maiorQue(console.log);
console.log(funcRef(50));

// function MenorQue(writer){
//     let num = (Math.random() * 100).toFixed();
//     console.log("Random: " + num);

//     return function(valor){
//         return valor < num
//     };
// }

// const funcRef2 = maiorQue(console.log);
// console.log(funcRef2(30));