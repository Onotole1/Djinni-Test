//
//  PrimeGenerator.cpp
//  Prime
//
//  Created by Анатолий Спитченко on 21.07.2018.
//  Copyright © 2018 Анатолий Спитченко. All rights reserved.
//

#include <stdio.h>
#include <cmath>
#include "PrimeGeneratorImpl.hpp"

namespace Prime {

    std::shared_ptr<PrimeGeneratorDjinni> PrimeGeneratorDjinni::create() {
        return std::make_shared<PrimeGeneratorImpl>();
    }

    PrimeGeneratorImpl::PrimeGeneratorImpl() {
    }

    std::vector<int32_t> PrimeGeneratorImpl::generate_prime(int32_t amount) {
        int *a = new int[amount + 1];
        std::vector<int32_t> result(0);
        for (int i = 0; i < amount + 1; i++) {
            a[i] = i;
        }
        for (int p = 2; p < amount + 1; p++) {
            if (a[p] != 0) {
                result.push_back(a[p]);
                for (int j = p * p; j < amount + 1; j += p)
                    a[j] = 0;
            }
        }

        delete[](a);

        return result;
    }
}
