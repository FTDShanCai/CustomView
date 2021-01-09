package com.demo.lib;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/11/11 0011
 * description:类生成代理类
 * ******************************
 */
public class ClassCreatorProxy {
    private String mBindingClassName;
    private String mPackageName;
    private TypeElement mTypeElement;
    private Map<String, TypeElement> mVariableElementMap = new HashMap<>();

    public ClassCreatorProxy(TypeElement classElement) {
        this.mTypeElement = classElement;
//        PackageElement packageElement = elementUtils.getPackageOf(mTypeElement);
//        String packageName = packageElement.getQualifiedName().toString();
//        String className = mTypeElement.getSimpleName().toString();
//        this.mPackageName = packageName;
        this.mBindingClassName = "WXPayEntryActivity";
    }

    public void setPackageName(String packageName) {
        mPackageName = packageName;
    }

    public void putElement(String pkName, TypeElement element) {
        mVariableElementMap.put(pkName, element);
    }

    /**
     * 创建Java代码
     *
     * @return
     */
    public TypeSpec generateJavaCode() {
        ClassName activity = ClassName.get("android.app", "Activity");
//        ClassName iwxApiEventHandler = ClassName.get("com.tencent.mm.sdk.openapi", "IWXAPIEventHandler");
        ClassName log = ClassName.get("android.util", "Log");

        TypeSpec bindingClass = TypeSpec.classBuilder(mBindingClassName)
                .superclass(activity)
                .addField(FieldSpec.builder(log, "mLog", Modifier.PRIVATE).build())
//                .addSuperinterface(iwxApiEventHandler)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(createMethod())
                .build();
        return bindingClass;

    }

    private MethodSpec createMethod() {
        ClassName bundle = ClassName.get("android.os", "Bundle");
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onCreate")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(bundle, "savedInstanceState");

        methodBuilder.addCode("super.onCreate(savedInstanceState);");
        methodBuilder.addCode("Log.d(\"ftd\",\"hello\");");
        return methodBuilder.build();
    }

    /**
     * 加入Method
     */
    private MethodSpec generateMethods() {
        ClassName host = ClassName.bestGuess(mTypeElement.getQualifiedName().toString());
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(host, "host");

        for (String pkName : mVariableElementMap.keySet()) {
            TypeElement element = mVariableElementMap.get(pkName);
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();
            methodBuilder.addCode("host." + name + " = " + "(" + type + ")(((android.app.Activity)host).findViewById( " + pkName + "));");
        }
        return methodBuilder.build();
    }


    public String getPackageName() {
        return mPackageName;
    }
}