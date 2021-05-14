if(document.querySelector('input[name="username"]').value != ""){
    var items = shoppingCart.get();
    var sum = 0;
    for(var i=0;i<items.length;i++){
        if((document.querySelector('input[name="username"]').value)==items[i].username){
            sum++;
        }
    }
    document.querySelector('a[class="cart-nav"] span').innerHTML = "("+sum+")";
}
else {
    document.querySelector('a[class="cart-nav"] span').innerHTML = "";
}
