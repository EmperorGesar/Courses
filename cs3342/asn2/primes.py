# Compute prime numbers with yield


def primes(N):
    n = 1
    while n < N:
        isPrime = False
        while not isPrime:
            isPrime = True
            n = n + 1
            for i in range(2, n - 1):
                if n % i == 0:
                    isPrime = False
        if n <= N:
            yield n
        else:
            raise StopIteration("Out of range.")
    raise StopIteration("Out of range.")


n = primes(11)
print(next(n))
print(next(n))
print(next(n))
print(next(n))
print(next(n))
print(next(n))
