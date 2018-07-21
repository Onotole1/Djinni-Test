cmake_minimum_required(VERSION 3.4.1)

file(GLOB prime_sources
    ../djinni/support-lib/jni/*.cpp
    ${PROJECT_SOURCE_DIR}/src/main/jni/djinnigenerated/jni/*.cpp
    ${PROJECT_SOURCE_DIR}/src/main/cpp/*.cpp
)

add_library(prime SHARED ${prime_sources})

# include directories for header files
include_directories(
    ../djinni/support-lib/
    ../djinni/support-lib/jni/
    ${PROJECT_SOURCE_DIR}/src/main/jni/djinnigenerated/cpp/
    ${PROJECT_SOURCE_DIR}/src/main/jni/djinnigenerated/jni/
    ${PROJECT_SOURCE_DIR}/src/main/cpp/
)

target_link_libraries(prime)