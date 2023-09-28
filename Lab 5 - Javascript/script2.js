const images = ['./img/image1.jpg', './img/image2.jpg', './img/image3.jpg',
'./img/image4.jpg', './img/image5.jpg', './img/image6.jpg',
'./img/image7.jpg', './img/image8.jpg', './img/image9.jpg']

const freePositions = [0,0,0,0,0,0,0,0,0]

function onDrag(e) {
    e.dataTransfer.setData('text', e.target.id);
}
  
function allowDrop(e) {
    e.preventDefault();
}
  
function onDrop(e) {
    e.preventDefault();
    const draggedImageId = e.dataTransfer.getData('text');
  
    const draggedImageElement = document.getElementById(draggedImageId);
    const droppedImageElement = e.target;
    const draggedImageParent = draggedImageElement.parentElement;
    const droppedImageParent = droppedImageElement.parentElement;
  
    draggedImageParent.removeChild(draggedImageElement);
    droppedImageParent.removeChild(droppedImageElement);
  
    draggedImageParent.appendChild(droppedImageElement);
    droppedImageParent.appendChild(draggedImageElement);
  
    isPuzzleFinished();
    // draggedImageParent.appendChild(droppedImageElement);
    // droppedImageParent.appendChild(draggedImageElement);
    // draggedImageParent.removeChild(draggedImageElement);
    // droppedImageParent.removeChild(droppedImageElement);
    // if(isPuzzleFinished() == 1)
    //   alert("You won!");
}

function isPuzzleFinished() {
    const imgs = document.querySelectorAll("img");

    let counter = 0;
    for (let img of imgs) {
      if (img.id !== `img${counter}`) {
        return;
      }
      counter ++;
    }
    // return 1;
    setTimeout(()=>alert("You won"), 1000);
}

function initializePuzzle() {
  
    // add random images to the 9 cells of the puzzle
    for (let piece of this.pieces) {
      let index = Math.floor(Math.random() * 9)

      // ALLOW CELLS TO HAVE IMAGES DROPPED ON THEM
      piece.setAttribute('ondragover', allowDrop);
      piece.setAttribute('ondrop', onDrop);

      // ADD IMAGES TO CELLS
      while (freePositions[index] === 1) {
        index = Math.floor(Math.random() * 9)
      }
      freePositions[index] = 1;
      // creating the img node that will display the desired piece.
      const imgNode = document.createElement('img');
      imgNode.src = images[index];
      imgNode.setAttribute('id', `img${index}`);
      imgNode.setAttribute('ondragstart', onDrag);

      // append the piece to the puzzle
      piece.appendChild(imgNode);
    }

    document.addEventListener('dragstart', onDrag);
    document.addEventListener('dragover', allowDrop);
    document.addEventListener('drop', onDrop);
}

pieces = document.querySelectorAll(".row .col")
initializePuzzle()
// let going = 1
// while (going == 1){
//     if (isPuzzleFinished() === 1){
//         alert("You win!")
//         going = 0;
//     }
// }