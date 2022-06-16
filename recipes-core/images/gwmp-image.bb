
require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL_append = " gwmp"
IMAGE_INSTALL_append = " python3-pip"
DISTRO_FEATURES += "wifi"