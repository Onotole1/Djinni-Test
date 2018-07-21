//
//  PrimeGenerator.h
//  Prime
//
//  Created by Анатолий Спитченко on 21.07.2018.
//  Copyright © 2018 Анатолий Спитченко. All rights reserved.
//

#pragma once

#include <vector>
#include <prime_generator_djinni.hpp>

namespace Prime {
    class PrimeGeneratorImpl: public Prime::PrimeGeneratorDjinni {
    public:
        PrimeGeneratorImpl();

        std::vector<int64_t> generate_prime(int64_t amount);
    };
}