// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from prime.djinni

#pragma once

#include <cstdint>
#include <memory>
#include <vector>

namespace Prime {

/** definitition of the C++ interface to be called from Java */
class PrimeGeneratorDjinni {
public:
    virtual ~PrimeGeneratorDjinni() {}

    static std::shared_ptr<PrimeGeneratorDjinni> create();

    virtual std::vector<int64_t> generate_prime(int64_t amount) = 0;
};

}  // namespace Prime
