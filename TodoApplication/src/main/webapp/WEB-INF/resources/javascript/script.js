
console.log(document.getElementById("entry").classList.value);
document.getElementById("entry").classList.value = JSON.parse(localStorage.getItem("classList"));

const add = (e) => {
    document.getElementById("entry").classList.toggle("invisible");
    localStorage.setItem("classList", JSON.stringify(document.getElementById("entry").classList.value));
    document.getElementById("entry").classList.value = JSON.parse(localStorage.getItem("classList"));
    console.log(document.getElementById("entry").classList.value);
};




