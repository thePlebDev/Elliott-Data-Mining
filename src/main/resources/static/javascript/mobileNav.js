//GETTING THE MOBILE NAV BURGER ICON

    const mobileNavContainer = document.getElementById("mobileNavMenu");
    const mobileNavOpenButton = document.getElementById("burgerOpen")
    const mobileNavCloseButton = document.getElementById("burgerClose");

    if(mobileNavOpenButton === null){
    console.log("IT IS NULL")
    }
    mobileNavOpenButton.addEventListener("click",onNavOpenButtonClick)
    mobileNavCloseButton.addEventListener("click",onNavButtonClose)
    window.addEventListener('resize', reportWindowSize);


// opens the mobile menu when clicked
function onNavOpenButtonClick(){
    console.log("OPEN")
    mobileNavContainer.style.display = "block";
    mobileNavOpenButton.style.display = "none"
    mobileNavCloseButton.style.display = "block";

}

//closes the mobile menu when clicked
function onNavButtonClose(){
    console.log("CLOSE")
    mobileNavContainer.style.display = "none";
    mobileNavOpenButton.style.display = "block"
    mobileNavCloseButton.style.display = "none";

}

//THIS CAUSES A UI BUG AND NEEDS TO BE FIXED LATER
function reportWindowSize() {
    
    
    if(window.innerWidth >= 715){
        mobileNavContainer.style.display = "none";
        mobileNavOpenButton.style.display = "none";
        mobileNavCloseButton.style.display = "none";
    }else{
        mobileNavOpenButton.style.display = "block";

    }
  }