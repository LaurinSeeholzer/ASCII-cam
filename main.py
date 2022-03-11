import imp
from cv2 import VideoCapture, imshow, imwrite, waitKey, destroyWindow
from PIL import Image

characters = "MN@#$o;:,. "

cam = VideoCapture(0)

result, image = cam.read()

if result:
    imwrite("img.jpg", image)
    image = Image.open('img.jpg')
    image = image.resize((3,3))
    photo = image.convert('RGB')

    width = photo.size[0]
    height = photo.size[1]

    for y in range(0, height):
        row = ""
        for x in range(0, width):
            RGB = photo.getpixel((x,y))
            print(RGB)

else:
    print("No image detected. Please! try again")
