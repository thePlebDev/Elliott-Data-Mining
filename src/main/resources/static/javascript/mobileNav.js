//GETTING THE MOBILE NAV BURGER ICON

    const mobileNavContainer = document.getElementById("mobileNavMenu");
    const mobileNavOpenButton = document.getElementById("burgerOpen")
    const mobileNavCloseButton = document.getElementById("burgerClose");

    mobileNavOpenButton.addEventListener("click",onNavOpenButtonClick)
    mobileNavCloseButton.addEventListener("click",onNavButtonClose)


// opens the mobile menu when clicked
function onNavOpenButtonClick(){
    
    mobileNavContainer.style.display = "block";
    mobileNavOpenButton.style.display = "none"
    mobileNavCloseButton.style.display = "block";

}

//closes the mobile menu when clicked
function onNavButtonClose(){
    mobileNavContainer.style.display = "none";
    mobileNavOpenButton.style.display = "block"
    mobileNavCloseButton.style.display = "none";

}