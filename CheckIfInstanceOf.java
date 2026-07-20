public var CheckIfInstanceOf = function(obj, classFunction) {
    // If the object is null/undefined or the classFunction isn't a valid constructor/function
    if (obj === null || obj === undefined || typeof classFunction !== 'function') {
        return false;
    }

    // Traverse up the prototype chain
    let currPrototype = Object.getPrototypeOf(obj);
    while (currPrototype !== null) {
        if (currPrototype === classFunction.prototype) {
            return true;
        }
        currPrototype = Object.getPrototypeOf(currPrototype);
    }

    return false;
};

