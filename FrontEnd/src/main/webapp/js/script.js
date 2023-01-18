function menu_show(x) {
    x.classList.toggle("change");
    if (document.getElementById('header_nav').style.display == 'block') {
        document.getElementById('header_nav').style.display = 'none'
    }
    else {
        document.getElementById('header_nav').style.display = 'block'
    }
}
