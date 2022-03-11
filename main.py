from cv2 import *

characters = "MN@#$o;:,. "

cam = VideoCapture(0)

result, image = cam.read()
